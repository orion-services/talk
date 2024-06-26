---
layout: default
title: Create Message
parent: Features
nav_order: 3
---

# Create Message

* A client sends a text, user hash and the channel hash to the service. Note: if
  the user hash or channel hash does not exist, the service will create in the
  data base.
* The service verifies if user hash and channel hash are note empty, persist the
  data and returns the a JSON object representing a message.

## HTTPS Endpoints

* /talk/message/create
* Method: POST
* Consumes: application/x-www-form-urlencoded
* Produces: application/json
* Examples:

  * Example of request:

    ```shell
      curl -X 'POST' \
        'http://localhost:8080/talk/message/create' \
        -H 'accept: application/json' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'text=hello%20world&userHash=12345&channelHash=54321'
    ```

  * Example of response: Message in JSON (application/json;charset=UTF-8 ).

    ```json
        {
            "text": "hello world",
            "hash": "b22be466-50d0-4df2-94ba-e752f0d255f7",
            "user": {
                "hash": "12345"
            },
            "channel": {
                "hash": "54321"
            }
        }
    ```

## Https endpoints exceptions

In the use case layer, exceptions related with arguments will be
IllegalArgumentException. However, in the RESTful Web Service layer will be
transformed to Bad Request (HTTP 400).

## GraphQL

The message also can be created using GraphQL. The following example shows how
through a mutation in `/graphql` endpoint.

  * Example of request:

    ```shell
        curl  -X POST \
            'http://localhost:8080/graphql' \
            --header 'Accept: */*' \
            --header 'Content-Type: application/json' \
            --data-raw '{"query":"mutation addMessage {\n  createMessage(\n    text : \"Hello World\"\n    userHash: \"12345\"\n    channelHash: \"54321\"\n  )\n  {\n    text\n    hash\n    user {\n      hash\n    }\n    channel{\n      hash\n    }\n  }\n}","variables":"{}"}'

    ```

  * Example of mutation:

    ```json
        mutation addMessage {
            createMessage(
                text : "Hello World"
                userHash: "12345"
                channelHash: "54321"
            )
            {
                text
                hash
                user {
                    hash
                }
                channel{
                    hash
                }
            }
        }
    ```

* Example of answer:

    ```json
        {
            "data": {
                "createMessage": {
                    "text": "Hello World",
                    "hash": "b22be466-50d0-4df2-94ba-e752f0d255f7",
                    "user": {
                        "hash": "12345"
                        },
                        "channel": {
                            "hash": "54321"
                        }
                }
            }
        }
    ```
