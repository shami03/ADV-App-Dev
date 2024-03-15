import React from 'react'
import './Login.css'
function Login() {
  return (
<div className='contmax'>    
<div class="container">
      <div class="login">
         <div class="container">
              <h1>Log in</h1>
              <input type="email" placeholder="Email"/>
              <input type="password" placeholder="Password"/><br/>
              <input type="checkbox"/><span>Remember me</span>
              <br/>
              <h4>Forgot password?</h4>
              <button>Log in</button>
                
         </div>
      </div>
      <div class="register">
          <div class="container">
              <i class="fas fa-user-plus fa-5x"></i>
              <h2>Hello!</h2>
              <p>Enter your personal details and start journey with us</p>
              <button>Register <i class="fas fa-arrow-circle-right"></i></button>
          </div>
      </div>  
    </div>
    </div>
  )
}

export default Login