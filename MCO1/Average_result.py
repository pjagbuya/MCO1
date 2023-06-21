
from os.path import exists


Raw_List = []
LineLocation = 100;

IsFileExist = False
while not IsFileExist:

    sFileName = "Output.txt"
    IsFileExist = exists(sFileName)
    if not IsFileExist:
        print("Can't find file, make sure you have .txt or spelling is correct")


with open(file=sFileName,mode="r", encoding='utf8') as DataLines:
    newRawList = []
    Raw_List = DataLines.readlines()

    for item in Raw_List:
        if "average" in item:
            print(item.split(':')[1].split('\n')[0])








