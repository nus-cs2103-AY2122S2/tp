### View All Clients and Procedures: `list`

Lists out all the clients and their respective procedures saved. 
This feature will be used to show clients on GUI. No arguments required.
Example: 
- User: list
- Terminal returns: 
1. Apple, 9XXXXXXX, Apple Road
    1. Fixed the intranet issue
    2. Replace router in 3rd floor
2. Singtel, 8XXXXXXX, Singtel Road
    1. Cable split
    2. Reconnected broadband services

##Command Summary
| Command | Syntax | Example |
| ----- | ----- | ----- |
| Add Client | _addClient n/\<NAME> p/\<PHONE_NUMBER> a/\<ADDRESS>_| _addClient n/Apple Inc p/9XXXXXXX a/apple road_ |
| Delete Client | _deleteClient \<INDEX>_ | deleteClient 1 |
| Add Procedure | _addProc \<CLIENT INDEX> \<Procedure>_ | _addProc 1 Check router’s connection with the modem_ |
| Delete Procedure | _deleteProc \<CLIENT INDEX> \<PROCEDURE INDEX>_ | _deleteProc 1 3_ |
| List All Clients | _list_ | _list_ |

