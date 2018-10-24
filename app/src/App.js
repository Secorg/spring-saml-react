import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor(props){
    super(props);
    this.state = { profile:[] }
  }

  componentDidMount(){
      fetch('/profile')
          .then(response => response.json())
          .then(profile => this.setState({ profile }));
  }
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Landing page: Welcome {this.state.profile.email}
          </p>
          <a
            className="App-link"
            href="/saml/logout?local=true"
          >
            Logout
          </a>
        </header>
      </div>
    );
  }
}

export default App;
