# points
MC plugin for teleporting to some points

# Commands
`/point <pointName>` - Teleport you to point with name `pointName` *(requires `points.tp` perm)*

`/point <pointName> <player>` - Teleport `player` to point with name `pointName` *(requires `points.tp.other` perm)*

`/points` - view list of points *(requires `points.tp.control` perm)*

`/points [add/remove] <pointName>` - add or remove point with name `pointName` *(requires `points.tp.control` perm)*

**NOTE:** when you add a new point, your current coordinates are used for it

# Permissions
### points.tp
Default gives to all players. Access for teleporting by points

### points.tp.other
*children: `points.tp`*

Default gives to ops. Access to teleport other player by points.

### points.control
*children: `points.tp`*
Default gives to ops. Access to create or remove a points.

# Download
You can download last release [here](https://github.com/teleport2/points/releases).
