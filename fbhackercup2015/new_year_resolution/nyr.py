#!/usr/bin/python
import numpy as np
import sys
f=open(sys.argv[1])
t=int(f.readline())
for i in range(1,t+1):
	desired_values = map(lambda x:int(x), f.readline().strip().split())
	nf = int(f.readline())
	params = []
	for j in range(0,len(desired_values)):
		params.append([])
	for j in range(0,nf):
		nutrition_values = map(lambda x:int(x), f.readline().strip().split())
		for k in range(0,len(nutrition_values)):
			params[k].append(nutrition_values[k])	
	if nf == 1:
		x=[]
		for j in range(0,len(desired_values)):
			x.append(desired_values[j]/params[j][0])
	else:
		a=np.array(params)
		print "Dimension:" + str(a.ndim)
		print "Shape" + str(a.shape)
		b=np.array(desired_values)
		print params
		print desired_values
		x = np.linalg.solve(a, b)
	has_solution="yes"
	print x

	print "Case %d: %s" % (i,has_solution)