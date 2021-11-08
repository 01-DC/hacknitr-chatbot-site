import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css'
import { BrowserRouter as Router } from 'react-router-dom';
import Auth0ProviderWithHistory from './auth/auth0-provider-with-history';


ReactDOM.render(
  <Router>
    <Auth0ProviderWithHistory>
    <App />
    </Auth0ProviderWithHistory>
  </Router>,
  document.getElementById('root')
);
