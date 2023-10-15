# serverless

HUGE ISSUE
1. How can the aws lambda (deployed by serverless) connect to an AWS database.
2. I finally figured it out.  Took me a couple of hours.
3. It all comes down to 2 things
4. Thing one - configure serverless.yml to allow certain actions.
5. Thing two - configure the lambda function to use the right vpc, sq, etc...
6. How did I figure out these details
7. Thing one - I found a stackoverflow post
8. Thing two - I found a blog post showing a lot of details
9. And along the way, I needed to create an EC2 instance with DB access to follow the tutorials

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

***** So, here's the solution *****
  1. Open an incognito window and go to your start URL for AWS.  My URL looks something like this https://d-<???????>.awsapps.com/start
  2. Go through the MFA sign on process
  3. Click on your account
  4. Click on "Command line or programmatic access"
  5. I'm on a mac, so it might work different for you, but, what worked for me, was option 1.  Just copy those commands and execute from the terminal window

So, now I can run sls deploy and it's up and running, just like that.

Now.... how do I hit it??

**** so... ****
when you run sls deploy it returns and endpoint url
append pets to the end of the url

https://<?magicnumber?>.execute-api.us-west-2.amazonaws.com/dev/pets


Here's something I get stuck on...
1. I want to have an enum to represent the valid states and substates
2. A state/substate should also be a domain object
3. It needs a constructuor for json deserialization
4. Not sure exactly how to explain the problem
5. Except that it doesn't work unless you do everything right :)

