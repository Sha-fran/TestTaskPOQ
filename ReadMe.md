# Test Task POQ

## Description:

Short one screen test task with getting list of repositories
from https://api.github.com/orgs/square/repos and displaying at least name and description of
repositories in the scrollable list using favorite architecture approach

## Features

- only one feature - displaying scrollable list of name and description of repositories
- in case of many features instead of ui module I will create a catalogue "feature" where place all
  feature modules

# Architecture

I've created 3 main layers (MVVM approach):

- UI (Presentation): with Screen on Jetpack Compose, ViewModel which depends from Domain and tests
  for a ViewModel
- Domain: with UseCase, needed entity, interface of the Repository and tests for a UseCase
- Data: which depends from Domain and with a repository where app is getting information from the
  server and map it into the entity

And Core module which provides useful reusable data and can be connected to any other modules

# Tests

I've created example of the tests for with using MockK library

# Opportunities

We can add a local DB (Room, Realm) for saving coming data in the device memory, if needed.
Create single source of all sizes, colors etc and move it into the core module.