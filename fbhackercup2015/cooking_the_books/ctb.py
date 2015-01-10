#!/usr/bin/python
import sys
f=open(sys.argv[1])
t=int(f.readline())
def smallest_largest_digit_index(i,ln, zero_ok_for_min):
	if ln == 1:
		return (0,i,0,i)
	minval=sys.maxint
	maxval=-1
	ln=ln-1 # 3
	mini=-1
	maxi=-1
	idx=0
	#print ln
	exp=10**ln

	while(exp >= 1):
		digit = i/exp # 1524/10**3 = 1 , 524/10**2 == 5, 24/10**1 = 2, 4/1 = 4
		# print "Exp=%d, Digit=%d i=%d" % (exp,digit,i)
		if digit < minval and (digit != 0 or zero_ok_for_min):
			minval=digit # minval = 1
			mini=idx #mini=0
		if digit > maxval:
			maxval = digit #max=5
			maxi=idx #maxi=1

		ln=ln-1 # 0
		idx=idx+1 # 3
		i=i%exp # 524, 24, 4
		exp=10**ln
		
	return (mini,minval,maxi,maxval)
def find_min_max(num_str, zeroes_at_mostsig):
	nstr = num_str[1:] #1524
	nint = int(nstr) #1524
	#print "len=%d" % len(nstr)
	imind,mind,imaxd,maxd=smallest_largest_digit_index(nint, len(nstr), zeroes_at_mostsig) # 1524, 4, False -> 0,1,1,5
	# print "imind %d mind %d imaxd %d maxd %d" % (imind,mind,imaxd,maxd)
	imaxd=imaxd+1 #2
	imind=imind+1 #1
	msd = int(num_str[0]) #3
	max_val = int(num_str) #31524
	min_val = max_val # 31524
	if msd < maxd:
		max_val = int(str(maxd) + num_str[1:imaxd] + str(msd) + num_str[imaxd+1:]) # "5"+"1"+"3"+"24"
	if msd > mind:
		if mind != 0 or zeroes_at_mostsig:
			min_val = int(str(mind) + num_str[1:imind] + str(msd) + num_str[imind+1:])
	return (max_val,min_val)
for i in range(1,t+1):
	original = f.readline().strip()
	orig_int = int(original)
	minv=orig_int
	maxv=orig_int
	zeroes_at_mostsig=False
	while len(original) > 1:
		cmaxv,cminv = find_min_max(original,zeroes_at_mostsig) # 31524 False
		# print minv
		# print maxv
		# print orig_int
		if cmaxv > maxv:
			maxv = cmaxv
		if cminv
		if minv < orig_int and maxv > orig_int:
			break
		zeroes_at_mostsig = zeroes_at_mostsig or True
		original = original[1:]
		# original=""
	print "Case #%d: %d %d" % (i,minv,maxv)


# 3 [1,5,2,4] -> 3 [1,2,4,5]
# 1 [2,0] -> 1 [0,2]