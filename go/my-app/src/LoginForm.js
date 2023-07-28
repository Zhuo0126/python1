import React, { useState } from 'react';
import './LoginForm.css'; // 导入自定义的 CSS 样式
import { useNavigate } from 'react-router-dom';
 
const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const nevigate =useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    //處理登錄表單提交邏輯
    
    const loginData = {
      username: username,
      password: password,
    };

    //發送port請求將數據傳遞給後端API
    fetch('http://localhost:8080/api/login',{
      method :'POST',
      headers:{
        'Content-Type':'application/json',
      },
      body :JSON.stringify(loginData),
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      if (data.success) {
        nevigate('/reservation');
      } else {
        
      }
    })
    .catch((error) => {
      console.error('請求出錯:',error)
    });
  };

  return (
    <form className="custom-body reservation-form1" onSubmit={handleSubmit}>
      <h2>用戶登錄</h2>
      <label>
        用戶名：
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
      </label>
      <br />
      <label>
        密碼：
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </label>
      <br />
      <button type="submit">登錄</button>
    </form>
  );
};

export default LoginForm;