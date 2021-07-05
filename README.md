Welcome to **Master of Renaissance.**

For other information on messages please check /Documentation/DataDiagram.pdf

Errata Corrige: the ASCII "art" is visible only in wsl2, terminal must be in full screen otherwise the faith track is printed incorrectly

This game is a digital version of the board game Master of Renaissance by Cragno Games®.

Game is designed for 1 to 4 player(s).

We are honest developers so we admit that the game at this time (07/01/2021) would be considered a pre-alpha version. There are still a lot of bugs and problems in different parts of the code.

The game has these features implemented:

-Socket communication for playing multiplayer (2-4 player(s))

-Online game for 1 player, the Server automatically performs “Lorenzo the Magnificent” actions

-Command Line Interface, tested on Windows cmd and WSL2 but should work on every single shell (ASCII ART may break in certain cases due to colors)

-Graphical User Interface has been completely drawn and coded but has a lot of problems due to poor testing.

-Local Single Player only works on CLI due to communication problems.

-The game is predisposed to have a parameters editor, all the editable parameters are parsed at the game start based on json files, sadly at this time there is no built-in editor for these files

Installation:

The game only uses one .jar file. Users must use arguments to correctly run the jar, use either -s for server or -c for client. Other parameters are not compulsory and if missing the game will automatically load default settings.

Default settings:						Parameter:

Server

Port Number: 48745						-p Integer

`					`Client

Interface: CLI							-g for GUI

Hostname: loopback, 127.0.0.1					-h String

Port Number: 48745						-p Integer

There is not a real help command to guide the user in his actions but if a wrong command is inserted the Server will automatically respond with a list of valid commands for the current game phase:

In order to correctly start the game the server should already be running and the first player, after choosing online mode, inserting his nickname and typing the “join game” command should insert the “choose players” command, from now on every single player who will connect to the server after choosing a nickname and typing “join game” will be put in a waiting lobby, when the number of players connected is reached the game will start. All players must now insert the “setup game” command to choose their Resources/Leader Cards.

When the game setup is completed the first player can start playing the game, the other clients can only call view methods to see the status of the game.

Normally players should do exactly one action to end their turn but, in order to ease the testing phase, the “End Turn” command will work even if in his turn the player did not perform any action. (The upper limit of 1 action per turn is still implemented).

List of commands:

- Join game
- Choose players
- Setup game
- Move resources -first line -second line (integers)
- Buy resource -line||column -number
- Buy production card -column -line
- Activate leader card -position
- Discard leader card -position
- Card production -position
- Leader production -position
- Standard production
- End turn









Only for External Users:

I don’t really expect anyone to play this game, it is, in fact, almost unplayable at this date.

But if there’s anyone who’s mad enough to clone this repo I just want to let you know that even if the game is one of the ugliest and most buggy things you’ll ever see I’m kinda proud of it and it really means so much, it has been my first “big” project, it has for every one of the developers.

We put a lot of commitment and time in this and we, or at least I, will keep working on it until the game will be playable and \*almost\* stable. 

So, I want to personally thank you and if you’re a developer yourself feel free to suggest any improvement.

Only for next year students:

Let be honest, you’re kinda desperate if you landed here, but we were too, I suggest you look for other projects since you won’t find much here but don’t worry, you will eventually find a way to work it out.

Good luck my fellow aspirant engineer, the path is still very long.

Anyway, in conclusion, if you’re either a student or a user browsing through repos on GitHub and you’ve reached this point thank you again for your time, it really means a lot.

The developers:

Samuele Cannilla, Paolo Corsa, Lelio Casale.


