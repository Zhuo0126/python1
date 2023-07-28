import React, { useState } from 'react';
import './ReservationForm.css'; 

const ReservationForm = () => {
  const [name, setName] = useState('');
  const [date, setDate] = useState('');
  const [bitrh, setBirth] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    //處理預約表單提交邏輯
    console.log('提交預約：', name, date);
  };

  return (
    <form className="reservation-form" onSubmit={handleSubmit}>
      <h2>客人預約</h2>
      <label>
        姓名：
        <input className ="color" type="text" value={name} onChange={(e) => setName(e.target.value)} />
      </label>
      <br />
      <label>
        日期：
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
      </label>
      <br />
      <label>
        生日：
        <input type="date" value={bitrh} onChange={(e) => setBirth(e.target.value)} />
      </label>
      <br />
      <button type="submit">提交預約</button>
    </form>
  );
};

export default ReservationForm;
