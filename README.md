## Deploy a new version
sbt assembly
serverless deploy -f hello

## Invoke the function if using regular scala handler (does not work with curl)

serverless invoke -f hello -l --data '{"key1": "Hello", "key2": "to", "key3": "you"}'

## Invoke the function if using apigatewayhander (does work with curl)

curl -X POST --data '{"key1":"hello","key2":"from","key3":"curl"}' <endpoint>

## Delete the function
serverless remove

## aws SAM

see https://github.com/awslabs/aws-sam-cli