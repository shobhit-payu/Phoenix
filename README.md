# Phoenix
Payment engine

Application runs on port 9000, Actor system (coded using myConfig.conf) to run on port 9001.

To make remote router work,
> Create a similar application with actorSystem named as "remoteSystem" to run on port 9003.
> Create a router named "remoteRouter" on the remote application.
> Run Remote application on port 9002 --> sbt (enter), run 9002.
