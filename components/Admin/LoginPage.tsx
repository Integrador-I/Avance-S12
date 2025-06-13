"use client"
import React from 'react';
import LoginForm from './LoginForm';

const LoginPage: React.FC = () => {


  return (
    <div
      aria-label="Login Modal"
      role="dialog"
      aria-modal="true"
      className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4"
    >
      <div
        className="bg-white rounded-2xl shadow-2xl w-full max-w-md p-8
          animate-fadeIn
          dark:bg-gray-900 dark:text-gray-100
          "
        style={{ animationDuration: '0.3s' }}
      >
        <LoginForm/>
      </div>
    </div>
  );
};

export default LoginPage;