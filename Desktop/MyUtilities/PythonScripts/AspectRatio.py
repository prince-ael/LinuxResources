#=========================================================================================

def GCD(width, height) :
    
    if height % width == 0 :
       return width
    return GCD((height % width), width)

w = input("Width : ")
h = input("Height :");

d = GCD(w,h);

print "width : height = ",(w/d),":",(h/d)
