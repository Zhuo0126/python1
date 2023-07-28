
// import React from 'react';
// // import ReactDOM from 'react-dom';
// import { createRoot } from 'react-dom/client';
// // import ReservationForm from './ReservationForm';
// import LoginForm from './LoginForm';



// createRoot(document.getElementById('root')).render(<LoginForm />);


import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import App from './App'; // 导入你的App组件

ReactDOM.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
  document.getElementById('root')
);
