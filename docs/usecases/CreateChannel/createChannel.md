---
layout: default
title: Create Channel
parent: Features
nav_order: 4
---

## Create Channel

* A client sends two objects to the server, the channel with a name and a user
  with a name and hash. Besides that, the client also sends an authorization
  token (JWT) in the header of the request.
* The service verifies if the token is valid and the fields are not empty,
  persist the data and returns the a JSON object representing a channel.

## GraphQL

The channel only can be created using GraphQL. The following example shows how
through a mutation in `/graphql` endpoint.

  * Example of request:

```shell
    curl  -X POST \
  'http://localhost:8082/graphql' \
  --header 'Accept: */*' \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJvcmlvbi11c2VycyIsInVwbiI6InJvZHJpZ29AdGVzdGUuY29tIiwiZ3JvdXBzIjpbInVzZXIiXSwiY19oYXNoIjoiMTZhNjc4YjctNWE0NC00MWExLWIzNmEtNGE3NmE2NDU1MjQ0IiwiZW1haWwiOiJyb2RyaWdvQHRlc3RlLmNvbSIsImlhdCI6MTcxNTAwMTgxMiwiZXhwIjoxNzE3NTkzODEyLCJqdGkiOiI1YzZiNDg2YS0zNWE0LTQzYmYtYmVmMS04YTQ5MzI5ZjEyOWEifQ.cRDhwRZ0Yuel5revQy1D1FUhfPYZtxVFLyaTJrAdTY_7djnplIBrm_VwftoXUCngjZd2YoOId3GX7c2GtAYSt24aHNWMhg3OKwIWQCHr6izQ7IAvsVTqJeJzHlQtx6s4mA6kWV76Ea7oCMDydtz397WJpkvALSPR0QM-XTn5AQ6-K0axOrJBCnvnZR_wL4TJgUBg2qOmoHUlhdi5HNbADN1UL-t6sRPYtqtos0dXRdH7LQjKrlzs30pXaJMIy3-Xm_bUgaoN4yryGi55mubyYvtuTIa1Do9ICMRsLLpRk8NjFJ-gjAssriYIs0IvrQXnlCbYXwqY_tqDGey_VcRDEA' \
  --header 'Content-Type: application/json' \
  --data-raw '{"query":"    mutation addChannel {\n        createChannel(channel: {\n            name: \"My teste channel\"\n        },\n        user:{\n          name: \"Rodrigo\"\n          hash: \"16a678b7-5a44-41a1-b36a-4a76a6455244\"\n        })\n        {\n            name\n            hash\n            messages {\n                hash\n                text\n            }\n        }\n    }\n","variables":"{}"}'
```

  * Example of mutation:

```json
mutation addChannel {
    createChannel(channel: {
        name: "My teste channel"
    },
    user:{
        name: "Rodrigo"
        hash: "16a678b7-5a44-41a1-b36a-4a76a6455244"
    })
    {
        name
        hash
        messages {
            hash
            text
        }
    }
}
```

* Example of answer:

```json
{
    "data": {
        "createChannel": {
            "name": "My teste channel",
            "hash": "16ea8abc-dfc9-4984-9203-25620268e79a",
            "messages": []
        }
    }
}
```
