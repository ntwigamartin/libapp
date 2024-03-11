'use client'
import Image from "next/image";
import Link from "next/link";
import { FormEvent, useState } from "react";

export default function Home() {

  const [toggleform, setToggleForm] = useState(true);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = (e: any) => {
    e.preventDefault();
    !confirmPassword ? handleHttpRequest("authenticate") : (password !== confirmPassword ? alert("passwords do not match") : handleHttpRequest("register"));
      
  }

  const handleHttpRequest = async (path: string) => {
    try {
      const response = await fetch(`http://localhost:8080/api/v1/auth/${path}`, {
      method: "POST",
      headers: {"content-type": "application/json"},
      body: JSON.stringify({
        username: email,
        password: password
      })
    });
    const data = await response.json();
    if(data.status == 403) {
      alert(data.message);
    }
    console.log(data);
    } catch (error: any) {
      setError(error);
      alert(error.message);
    }
    
  }

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      {toggleform ? (
        <div className="flex flex-col justify-evenly border-2 border-zinc-300 w-80 h-96 ">
          <form onSubmit={handleSubmit} className="p-2 flex flex-col justify-around">
            <div className="flex justify-center">
              <h1 className="font-bold">Login to Proceed</h1>
            </div>
            <div className="flex flex-col pt-2">
              <label htmlFor="email">Email</label>
              <input 
              type="email"
              id="email" 
              placeholder="Enter email" 
              className="placeholder:italic placeholder:text-sm rounded-sm"
              onChange={(e)=> setEmail(e.target.value)}
              required
              />
            </div>
            <div className="flex flex-col pt-2">
              <label htmlFor="password">Password</label>
              <input 
              type="password"
              id="password" 
              placeholder="Enter Password" 
              className="placeholder:italic placeholder:text-sm rounded-sm"
              onChange={(e)=> setPassword(e.target.value)}
              required
              />
            </div>
            <div className="flex justify-center pt-2">
              <button className="btn btn-success btn-sm">Login</button>
            </div>
          </form>
          <div>
            <h3 className="p-2">
              Forgot password? Click <Link href={"/change-password"} className="link link-primary">here</Link> to change password
            </h3>
          </div>
          <h3 className="p-2">
              Do not have an account?
              <button 
                className="btn-xs bg-gray-400 hover:bg-green-600 rounded-lg"
                onClick={() => setToggleForm(false)}
                 >
                Sign Up
              </button>
          </h3>
        </div>
      ) : (
          <div className="flex flex-col justify-evenly border-2 border-zinc-300 w-80 h-96 ">
            <form onSubmit={handleSubmit} className="p-2 flex flex-col justify-around">
              <div className="flex justify-center">
                <h1 className="font-bold">Register Account</h1>
              </div>
              <div className="flex flex-col pt-2">
                <label htmlFor="email">Email</label>
                <input 
                type="email"
                id="email" 
                placeholder="Enter email" 
                className="placeholder:italic placeholder:text-sm rounded-sm"
                onChange={(e)=> setEmail(e.target.value)}
                required
                />
              </div>
              <div className="flex flex-col pt-2">
                <label htmlFor="password">Password</label>
                <input 
                type="password"
                id="password" 
                placeholder="Enter Password" 
                className="placeholder:italic placeholder:text-sm rounded-sm"
                onChange={(e)=> setPassword(e.target.value)}
                required
                />
              </div>
              <div className="flex flex-col pt-2">
                <label htmlFor="confirmpassword">Confirm Password</label>
                <input 
                type="password"
                id="confirmpassword" 
                placeholder="Re-enter Password" 
                className="placeholder:italic placeholder:text-sm rounded-sm"
                onChange={(e)=> setConfirmPassword(e.target.value)}
                required
                />
              </div>
              <div className="flex justify-center pt-2">
                <button className="btn btn-success btn-sm">Sign Up</button>
              </div>

            </form>
            <h3 className="p-2">
                Already have an account?
                <button
                  className="btn-xs bg-gray-400 hover:bg-green-600 rounded-lg"
                  onClick={() => setToggleForm(true)}
                >
                  Sign In
                </button>
            </h3>
          </div>
      )}
    </main>
  );
}
