# Test Task POQ

## Description:

Short one screen test task with getting list of repositories
from https://api.github.com/orgs/square/repos and displaying at least name and description of
repositories in the scrollable list using favorite architecture approach

## Features

- only one feature - displaying scrollable list of name and description of repositories

# Architecture

I've created 3 main layers (MVVM approach):

- UI (Presentation): with Screen on Jetpack Compose and ViewModel which depends from Domain
- Domain: with UseCase and needed entity
- Data: which depends from Domain and with a repository where app is getting information from the
  server and map it into the entity

And Core module which provides useful reusable data and can be connected to any other modules

# Tests

I've created example of the tests for a UseCase with using library Mockito and for a ViewModel -
with MockK

# Opportunities
We can add a local DB (Room, Realm) for saving coming data in the device memory, if needed.
Create single source of all sizes, colors etc and move it into the core module.