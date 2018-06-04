#-*- coding=utf-8 -*-
import numpy as np
import pandas as pd
from keras.preprocessing import sequence
from keras.models import Sequential
from keras.layers import Dense, Dropout, Embedding, LSTM, Bidirectional, Flatten, Activation
from sklearn.cross_validation import train_test_split
from keras import backend as K
from keras.optimizers import Adam


def R_Square(y_true, y_pre):
    RS = 1 - (K.sum((y_true - y_pre)**2))/(K.sum((y_true - K.mean(y_true))**2))
    return RS
#损失函数--欧式距离
def euclidean_loss(y_true, y_pre):
    return (1/2)*K.mean(K.square(y_true - y_pre))


data = pd.read_csv('temp.csv',header=0)
data = data.values
data = data/10000
X = data[:,:-1]
#X_mean = np.mean(X, axis=0)
#X = X - X_mean
y = data[:,-1]
y = y.reshape(len(y),1)
X_train = X[500:]
X_test = X[:500]
y_train = y[500:]
y_test = y[:500]
#X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.1, random_state=7)
X_train = X_train.reshape(-1, 1, 3)
X_test = X_test.reshape(-1, 1, 3)


model = Sequential()
model.add(LSTM(128, batch_input_shape=(None, 1, 3)))
model.add(Dense(1))


model.compile(optimizer=Adam(lr=0.0005), loss='mse',  metrics=[R_Square])

model.fit(X_train, y_train, epochs=5, verbose=1, validation_data=(X_test, y_test))
X = X.reshape(-1,1,3)
y_predict = model.predict(X)
y_predict = y_predict * 10000
y_predict = y_predict.reshape(len(y_predict))
y = y.reshape(len(y))
X = X.reshape(-1,3)
y = y * 10000
X = X * 10000
dataframe = pd.DataFrame({'1':X[:,0],'2':X[:,1],'3':X[:,2],'true':y,'predict_value':y_predict})

#将DataFrame存储为csv,index表示是否显示行名，default=True
dataframe.to_csv("轨交号.csv",index=False,sep=',')


