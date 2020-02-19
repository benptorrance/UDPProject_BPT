# UDPProject_BPT
# Ben Torrance
#
#	This directory contains the files for a UDP Client and Server application.
# The Client application takes a command from the user and can either send a packet
# to request a quote, or exit the program and send a packet to close the server.
# The Server "listens" for the packet from the Client application at port 2040 and once
# it receives this packet it will send back a packet that contains a quote that has
# been randomly chosen from a list of twenty quotes in the quote.csv file. The Client,
# at this point, is listening for a return package. Once it receives the return package,
# the Client then displays the quote and asks for another command. The Server also
# displays the timestamp that it started and also a list of timestamps when each request
# was received from the Client.
#
#
#	Running the UDPServer application:
#
#		The UDPServer application requires you to run the javac compiler and then
#	run the .class file that is created through that. There are no additional 
#	arguments, however, the quotes.csv must be in the same directory as the .class file.
#
#
#	Running the UDPClient application:
#
#		The UDPClient application requires you to run the javac compiler and then
#	run the .class file that is created through that. There are two additional
#	arguments, the first is the ip address of the machine that the Server is currently
#	running on and the second is the port 2040.
#

