#!/usr/bin/python
import sys
f=open(sys.argv[1])
t=int(f.readline())
for idx in range(1,t+1):
	candidate = f.readline().strip()
	maxv = candidate
	minv = candidate
	if len(candidate) > 1:
		i=0
		while i < len(candidate):
			to_replace = candidate[i]
			max_replacement = None
			max_replacement_idx = 1
			if to_replace < "9":
				for j in range(i+1,len(candidate)):
					#print "Max: To Replace = %s, candidate = %s" % (to_replace,candidate[j])
					if candidate[j] > to_replace:
						if max_replacement is None or candidate[j] > max_replacement:
							max_replacement = candidate[j]
							max_replacement_idx = j
				if max_replacement is not None:
					maxv = candidate[:i] + max_replacement + candidate[i+1:max_replacement_idx] + to_replace + candidate[max_replacement_idx+1:]
					break
			i=i+1
		i=0
		while i < len(candidate):
			to_replace = candidate[i]
			min_replacement = None
			min_replacement_idx = 1
			if to_replace > "0":
				for j in range(i+1,len(candidate)):
					#print "Min: To Replace = %s, candidate = %s" % (to_replace,candidate[j])
					if candidate[j] < to_replace:
						if (min_replacement is None or candidate[j] < min_replacement) and (candidate[j] != "0" or i > 0):
							min_replacement = candidate[j]
							min_replacement_idx = j
				if min_replacement is not None:
					minv = candidate[:i] + min_replacement + candidate[i+1:min_replacement_idx] + to_replace + candidate[min_replacement_idx+1:]
					break
			i=i+1
	print "Case #%d: %s %s" % (idx,minv,maxv)



