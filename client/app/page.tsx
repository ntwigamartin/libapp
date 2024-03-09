'use client'
import Image from "next/image";
import Link from "next/link";
import { useState } from "react";

export default function Home() {

  const [toggleform, setToggleForm] = useState(true);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      {toggleform ? (
        <div className="flex flex-col justify-evenly border-2 border-zinc-300 w-80 h-96 ">
          <form action="" className="p-2 flex flex-col justify-around">
            <div className="flex justify-center">
              <h1 className="font-bold">Login to Proceed</h1>
            </div>
            <div className="flex flex-col pt-2">
              <label htmlFor="">Email</label>
              <input type="text" placeholder="Enter email" className="placeholder:italic placeholder:text-sm rounded-sm"
              />
            </div>
            <div className="flex flex-col pt-2">
              <label htmlFor="">Password</label>
              <input type="text" placeholder="Enter Password" className="placeholder:italic placeholder:text-sm rounded-sm"
              />
            </div>
            <div className="flex justify-center pt-2">
              <button className="btn btn-success btn-sm">Login</button>
            </div>
          </form>
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
            <form action="" className="p-2 flex flex-col justify-around">
              <div className="flex justify-center">
                <h1 className="font-bold">Register Account</h1>
              </div>
              <div className="flex flex-col pt-2">
                <label htmlFor="">Email</label>
                <input type="text" placeholder="Enter email" className="placeholder:italic placeholder:text-sm rounded-sm"
                />
              </div>
              <div className="flex flex-col pt-2">
                <label htmlFor="">Password</label>
                <input type="text" placeholder="Enter Password" className="placeholder:italic placeholder:text-sm rounded-sm"
                />
              </div>
              <div className="flex flex-col pt-2">
                <label htmlFor="">Confirm Password</label>
                <input type="text" placeholder="Re-enter Password" className="placeholder:italic placeholder:text-sm rounded-sm"
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
