# redtrack

time tracking app for redmine using javafx

![Redtrack](https://i.imgur.com/RhfPN3i.png "Redtrack")

## config

config file is located at ~/.redtrack/config.properties must contain:
```
#url of the redmine installation
base_url=https\://my.url.com/path/to/redmine/
#user api key
api_key=foobar
```
and can optionally contain:
```
#only show issues assigned to the user
assigned_only=true
```
The API key can be retrieved in redmine at ```$base_url/my/account```