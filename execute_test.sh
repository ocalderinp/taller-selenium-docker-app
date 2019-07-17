#!/usr/bin/env bash

mvn clean test verify -Dhub=$HUB_TCP_ADDR:$HUB_TCP_PORT
