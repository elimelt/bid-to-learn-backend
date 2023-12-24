#!/bin/bash

# Check if yq is installed
if ! command -v yq &> /dev/null; then
    echo "yq is not installed. Please install it first."
    exit 1
fi

# Check if jq is installed
if ! command -v jq &> /dev/null; then
    echo "jq is not installed. Please install it first."
    exit 1
fi

# Check if file argument is provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <input.yaml>"
    exit 1
fi

INPUT_FILE="$1"


# Convert YAML to JSON
yq eval ". | tojson" "$INPUT_FILE" > "./src/main/resources/static/openapi.json"

echo "uploaded successfully"
