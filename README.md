
# Spring Boot SAML + React JS
----
Spring boot MVC application that authenticates using SAML 2.0 and React JS UI components.
## Development Guide
## 1. Install npm
Install npm using homebrew
## 2. Configure Identity Provider (Okta is an option).
Configure Identity Provider to handle authentication from our App.
Okta has free developer account https://developer.okta.com/.

Configure a SAML application https://developer.okta.com/standards/SAML/setting_up_a_saml_application_in_okta.

## 4. Clone saml-react application.
git clone https://github.com/Secorg/spring-saml-react.git

navigate to app folder and run npm install

## 3. Configure saml-react application with Identity provider.
Update application properties file for your identity provider.

## 4. Run saml react application.
Use run.sh shell script in the root of folder to build and run the app.

./run.sh app

Access : https://localhost:8443/

