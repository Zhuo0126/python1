import React from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import LoginForm from './LoginForm';
import ReservationForm from './ReservationForm';

const App = () => {
  return (
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/reservation" element={<ReservationForm />} />
      </Routes>
  );
};

export default App;
