#!/usr/local/bin/python

#name  IsOpen.py

import os

import sys

import socket

#first argument

host=sys.argv[1]

#second argument

port=int(sys.argv[2])

#socket try connect

def IsOpen(ip,port):
    print('ip %s prot %s'%(ip,port))
    s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

    try:

        s.connect((ip,port))

        s.shutdown(2)

        print( 'IP %s,port %d is open' %(ip,port)) 

        return True

    except Exception,err:
	print(err)
        #print( 'IP %s,port %d is down' %(ip,port)) 

        return False

if __name__=='__main__':
	IsOpen(host,port)
	



