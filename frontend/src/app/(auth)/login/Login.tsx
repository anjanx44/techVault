import React, { useState } from 'react';

/**
 * Login Component: Renders the central, styled login form.
 * This component handles local state for inputs and form submission logic.
 */
const Login = () => {
    // 1. State management for form inputs
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [rememberMe, setRememberMe] = useState(false);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [message, setMessage] = useState('');

    // 2. Form submission handler
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setIsSubmitting(true);
        setMessage('');

        // --- Start Simulated Login Logic ---
        setTimeout(() => {
            console.log('Login attempt:', { email, password, rememberMe });

            // Basic validation for demonstration
            if (email === 'test@example.com' && password === 'password') {
                setMessage(`Login successful for ${email}!`);
            } else {
                setMessage(`Login failed. Please check your credentials.`);
            }

            setIsSubmitting(false);
            // In a real application, replace this with your actual API call (e.g., Firebase, NextAuth).
        }, 1500);
        // --- End Simulated Login Logic ---
    };

    // Base class for input fields, configured for better focus styling (border-blue-600)
    const inputClass = "appearance-none relative block w-full px-3 py-3 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-lg focus:outline-none focus:ring-blue-500 focus:border-blue-600 transition duration-150 sm:text-sm";

    return (
        // This container defines the look and responsiveness of the card itself
        <div className="w-full max-w-md bg-white p-8 sm:p-10 rounded-xl shadow-2xl transition duration-500 ease-in-out transform hover:shadow-xl">

            <div className="text-center">
                {/* Login Icon (from your HTML) */}
                <svg xmlns="http://www.w3.org/2000/svg" className="h-10 w-10 mx-auto text-blue-600" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1" />
                </svg>
                <h1 className="mt-4 text-3xl font-extrabold text-gray-900">
                    Sign In to Your Account
                </h1>
                <p className="mt-2 text-sm text-gray-500">
                    Access articles and manage your subscription
                </p>
            </div>

            {/* Success/Error Message Display */}
            {message && (
                <div className={`mt-4 p-3 rounded-lg text-sm font-medium text-center ${
                    message.includes('successful') ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'
                }`}>
                    {message}
                </div>
            )}


            <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
                {/* Email Input */}
                <div>
                    <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-1">Email address</label>
                    <input
                        id="email"
                        name="email"
                        type="email"
                        autoComplete="email"
                        required
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className={inputClass}
                        placeholder="you@example.com"
                        disabled={isSubmitting}
                    />
                </div>

                {/* Password Input */}
                <div>
                    <label htmlFor="password" className="block text-sm font-medium text-gray-700 mb-1">Password</label>
                    <input
                        id="password"
                        name="password"
                        type="password"
                        autoComplete="current-password"
                        required
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className={inputClass}
                        placeholder="Enter your password"
                        disabled={isSubmitting}
                    />
                </div>

                <div className="flex items-center justify-between">
                    <div className="flex items-center">
                        {/* Remember Me Checkbox */}
                        <input
                            id="remember-me"
                            name="remember-me"
                            type="checkbox"
                            checked={rememberMe}
                            onChange={(e) => setRememberMe(e.target.checked)}
                            className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                            disabled={isSubmitting}
                        />
                        <label htmlFor="remember-me" className="ml-2 block text-sm text-gray-900">
                            Remember me
                        </label>
                    </div>

                    <div className="text-sm">
                        <a href="#" className="font-medium text-blue-600 hover:text-blue-500 transition duration-150">
                            Forgot your password?
                        </a>
                    </div>
                </div>

                {/* Submit Button */}
                <div>
                    <button
                        type="submit"
                        className="group relative w-full flex justify-center py-3 px-4 border border-transparent text-sm font-bold rounded-lg text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 transition duration-150 shadow-lg hover:shadow-xl disabled:bg-blue-400"
                        disabled={isSubmitting}
                    >
                        {isSubmitting ? 'Signing In...' : 'Sign in'}
                    </button>
                </div>
            </form>

            {/* Signup Link */}
            <div className="mt-6 text-center text-sm">
                <p className="text-gray-600">
                    Don't have an account?
                    {/* Link to your register route */}
                    <a href="/register" className="font-medium text-blue-600 hover:text-blue-500 transition duration-150">
                        Sign up for free
                    </a>
                </p>
            </div>

        </div>
    );
};

export default Login;
