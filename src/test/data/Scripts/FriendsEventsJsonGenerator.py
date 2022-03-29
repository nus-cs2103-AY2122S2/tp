import json
from random import randint
import string
import random
from datetime import datetime, timedelta
from random_address import real_random_address

letters = string.ascii_lowercase

''.join(random.choice(letters) for i in range(5))

persons = dict()

people = []
events = []

hours = ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
minutes = ["00","01","02","03","04","05","06","07","08","09"]
minutes2 = [str(10 + i) for i in range(50)]
minutes.extend(minutes2)

months = ["01","02","03","04","05","06","07","08","09","10","11","12"]
days = ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24", "25", "26", "27", "28"]


#Create random person
for _ in range(500):
    temp_dict = dict()
    temp_dict["name"] = ''.join(random.choice(letters) for i in range(5))
    temp_dict["phone"] = randint(1111111,9999999)
    temp_dict["email"] = ''.join(random.choice(letters) for i in range(5)) + "@gmail.com"
    temp_dict["address"] = real_random_address()['address1']
    random_log = dict()
    random_log["title"] = ''.join(random.choice(letters) for i in range(5))
    random_log["description"] = "".join(random.choice(letters) for i in range(10))
    temp_dict["logs"] = [random_log]
    temp_dict["tagged"] = ["".join(random.choice(letters) for i in range(5))]
    people.append(temp_dict)    

#Create random events
for _ in range(500):
    temp_dict = dict()
    temp_dict["name"] = ''.join(random.choice(letters) for i in range(5))
    temp_dict["description"] = "".join(random.choice(letters) for i in range(10))
    temp_dict["dateTime"] = f"{random.choice(days)}-{random.choice(months)}-{randint(2000,2025)} {random.choice(hours)}{random.choice(minutes)}"
    events.append(temp_dict)

persons["persons"] = people
persons["events"] = events

print(json.dumps(persons))