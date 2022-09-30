# Projet VOD

Here is the complete implementation of the vod subject.

## How to run
Start the server (src/server/Main.java), then the client (src/client/Main.java), sign up, log in with the same login then ask any movie.

## Bonuses
All the bonuses have been implemented:
- After a user signs up, the password is hashed with the CRC12 algorithm then the credentials are stored in a file.
- All the exceptions are handled, either client-side or server-side, on whichever it is logic.

## Even more bonus
If vlc is installed (only tested on Linux), the client will try to use it to read the video

On line 30 of [Streamer](src/server/Streamer.java), change the file path to one pointing to a video.
Then launch the server and the client and ask any video.