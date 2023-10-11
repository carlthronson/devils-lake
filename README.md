# devils-lake

Step 1, Uses serverless to create simple CRUD endpoints on AWS
Take a look at origin	https://github.com/serverless-guru/aws-serverless-java-container.git (fetch)
And https://github.com/serverless-guru/aws-serverless-java-container/blob/master/samples/springboot/pet-store/README.md

This shows you how you can HOST SIMPLE CRUD OPERATIONS ON AWS
  Steps to run this thing
  1. mvn package
  2. sls deploy

Yes, it's that simple.  Of course you need to install mvn and sls.  But, then you are ready to roll.

The first road block is the AWS CREDENTIALS NIGHTMARE
When I log in to AWS, I use multi-factor authentication.
How exactly do you do that from the command line??

I think you probably need some sort of reusable token session....
