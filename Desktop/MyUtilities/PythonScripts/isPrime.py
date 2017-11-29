#------This script determines whether a positive integer is prime number or not------------

def isPrime(n) :
    
    if n == 0 or n == 1 :
       return str(n)+" is not a prime number"
    if n == 2 or n == 3 :
       return str(n)+" is a prime number"
    
    sqrtOfN = int(n**0.5)
    div = 2;
    
    while div <= sqrtOfN :
          
          if n % div == 0:
             return str(n) + " is not a prime number"
          else :
             div += 1
    return str(n) + " is  a prime number"

n = input("The Number To Check : ")
print isPrime(n)


    
       
    
