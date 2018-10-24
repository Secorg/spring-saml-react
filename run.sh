#!/bin/bash

USAGE="
Options:
    help (or no args)
        Prints this dialogue
    app
        Build and run ui and payment-manager separately.
"

main() {
    if [[ $# -eq 0 || "$1" == "help" ]]; then
        # No args provided, print help
        echo "$USAGE"
        exit
    elif [[ "$(type -t $1)" == "function" ]]; then
        # First arg is a function in this script,
        # execute command as provided
        "$@"
    else
        # All other non-function args are delegated to docker-compose
        CMD="docker-compose $@"
        echo "Running: $CMD"
        $CMD
    fi
}

# This function builds and run the app
app() {
    set -o xtrace
     npm --prefix app/ run build && gradle bootRun
}
main "$@"