import sys

def CalculateDP(pixelWidth, density):
    return (pixelWidth*160.0)/density

def PixelsIn(dp, density):
    return (density / 160.0) * dp

pixelWidth = input("Pixel Width    :")
density   = input("Screen Density  :")
dp        = input("DP              :")

if(pixelWidth < 0):
     print"Calculated Pixels :",PixelsIn(dp,density)
else :
     print"Calculated DP     :",CalculateDP(pixelWidth,density)
