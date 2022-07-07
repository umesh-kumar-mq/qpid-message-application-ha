# qpid-message-application-ha

This is a qpid application to send and receive messages.

In this I have implemented Session pool, by which we are reusing the sessions again and again. 
And whenever any failure or restart happens it automatically refreshes the session pool with sessions with the new MASTER node.
