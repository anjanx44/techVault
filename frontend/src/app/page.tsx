'use client';

import Link from 'next/link';
import { useState, useEffect } from 'react';

export default function HomePage() {
  const [featuredPost, setFeaturedPost] = useState(null);
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Fetch featured posts
    fetch('http://localhost:8080/api/v1/posts/featured')
      .then(res => res.json())
      .then(data => {
        if (Array.isArray(data) && data.length > 0) {
          setFeaturedPost(data[0]);
        }
      })
      .catch(err => console.error('Error fetching featured posts:', err));
      
    // Fetch all posts
    fetch('http://localhost:8080/api/v1/posts')
      .then(res => res.json())
      .then(data => {
        if (Array.isArray(data)) {
          setPosts(data);
        }
        setLoading(false);
      })
      .catch(err => {
        console.error('Error fetching posts:', err);
        setLoading(false);
      });
  }, []);

  return (
    <div className="antialiased text-gray-800">
      {/* Header & Navigation */}
      <header className="bg-white shadow-md sticky top-0 z-10">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-4 flex justify-between items-center">
          {/* Logo/Site Title */}
          <Link href="/" className="text-2xl font-extrabold text-blue-600 tracking-tight">
            TechVault
          </Link>
          {/* Navigation Links */}
          <nav className="hidden md:flex space-x-6 items-center">
            <Link href="/" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">Home</Link>
            <Link href="/blog" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">Blog</Link>
            <Link href="#" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">About</Link>
            <Link href="/login" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">Login</Link>
            <Link href="/register" className="text-blue-600 border border-blue-600 px-3 py-1 rounded-full hover:bg-blue-50 transition duration-150">Register</Link>
          </nav>
          {/* Mobile Menu Icon */}
          <button className="md:hidden text-gray-600 hover:text-blue-600">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
              <path strokeLinecap="round" strokeLinejoin="round" d="M4 6h16M4 12h16m-7 6h7" />
            </svg>
          </button>
        </div>
      </header>

      {/* Main Content Grid */}
      <main className="container mx-auto px-4 sm:px-6 lg:px-8 mt-10 mb-20">
        
        {/* Featured Section */}
        {featuredPost && (
          <section className="bg-white p-8 rounded-xl shadow-lg mb-12">
            <span className="text-xs font-semibold uppercase tracking-wider text-blue-500 bg-blue-100 px-3 py-1 rounded-full">Featured</span>
            <h1 className="text-4xl sm:text-5xl font-extrabold text-gray-900 mt-4 mb-3 leading-tight">
              {featuredPost.title}
            </h1>
            <p className="text-lg text-gray-600 mb-6">
              {featuredPost.content}
            </p>
            <div className="flex items-center space-x-4">
              <img className="w-12 h-12 rounded-full object-cover" src="https://placehold.co/150x150/4f46e5/ffffff?text=JT" alt="Author Avatar" />
              <div>
                <p className="text-sm font-semibold text-gray-800">{featuredPost.user.username}</p>
                <p className="text-xs text-gray-500">{new Date(featuredPost.createdAt).toLocaleDateString()} · 8 min read</p>
              </div>
            </div>
          </section>
        )}

        {/* Articles Grid and Sidebar */}
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-10">
          
          {/* Articles Column */}
          <section className="lg:col-span-8 space-y-8">
            <h2 className="text-2xl font-bold text-gray-900 border-b pb-2 mb-4">Latest Articles</h2>

            {/* Dynamic Articles */}
            {loading ? (
              <div className="text-center py-8">Loading articles...</div>
            ) : (
              posts.filter(post => !post.featured).map((post) => (
              <article key={post.id} className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition duration-300">
                <div className="flex flex-col md:flex-row gap-6 items-start">
                  <div className="md:w-1/3">
                    <img className="w-full h-32 object-cover rounded-lg" src="https://placehold.co/400x250/3b82f6/ffffff?text=Article" alt="Article" />
                  </div>
                  <div className="md:w-2/3">
                    <p className="text-sm font-semibold text-purple-600 mb-1">TECH</p>
                    <h3 className="text-xl font-bold text-gray-900 mb-2">
                      <Link href={`/blog/${post.id}`}>{post.title}</Link>
                    </h3>
                    <p className="text-gray-600 text-sm mb-3">
                      {post.content.substring(0, 150)}...
                    </p>
                    <div className="text-xs text-gray-500">
                      {post.user.username} · {new Date(post.createdAt).toLocaleDateString()} · 5 min read
                    </div>
                  </div>
                </div>
              </article>
            ))
            )}

            {/* Load More Button */}
            <div className="text-center pt-4">
              <button className="bg-blue-600 text-white px-6 py-2 rounded-full font-semibold hover:bg-blue-700 transition duration-150 shadow-lg">
                Load More Articles
              </button>
            </div>
          </section>

          {/* Sidebar */}
          <aside className="lg:col-span-4 space-y-8">
            
            {/* Search Widget */}
            <div className="bg-white p-6 rounded-xl shadow-md">
              <h3 className="text-lg font-bold text-gray-900 mb-4 border-b pb-2">Search Articles</h3>
              <input 
                type="text" 
                placeholder="e.g., Dynamic Programming" 
                className="w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
              />
              <button className="w-full mt-3 bg-gray-200 text-gray-700 px-4 py-2 rounded-lg font-medium hover:bg-gray-300 transition duration-150">
                Search
              </button>
            </div>

            {/* Categories Widget */}
            <div className="bg-white p-6 rounded-xl shadow-md">
              <h3 className="text-lg font-bold text-gray-900 mb-4 border-b pb-2">Popular Categories</h3>
              <ul className="space-y-2">
                <li><Link href="#" className="text-sm text-blue-600 hover:underline">Machine Learning (14)</Link></li>
                <li><Link href="#" className="text-sm text-blue-600 hover:underline">Competitive Programming (22)</Link></li>
                <li><Link href="#" className="text-sm text-blue-600 hover:underline">Web Development (18)</Link></li>
                <li><Link href="#" className="text-sm text-blue-600 hover:underline">Financial Planning (8)</Link></li>
              </ul>
            </div>

            {/* Newsletter Widget */}
            <div className="bg-blue-600 p-6 rounded-xl shadow-md text-white">
              <h3 className="text-xl font-bold mb-3">Subscribe to the Newsletter</h3>
              <p className="text-sm mb-4 opacity-90">Get the latest tech tips delivered straight to your inbox every week.</p>
              <input 
                type="email" 
                placeholder="Your email address" 
                className="w-full p-3 border-none rounded-lg text-gray-900 focus:outline-none focus:ring-2 focus:ring-white"
              />
              <button className="w-full mt-3 bg-white text-blue-600 px-4 py-2 rounded-lg font-bold hover:bg-blue-100 transition duration-150">
                Sign Up Now
              </button>
            </div>
          </aside>
        </div>
      </main>

      {/* Footer */}
      <footer className="bg-gray-900 text-white">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-8 text-center">
          <p className="text-sm">&copy; 2025 TechVault. All rights reserved.</p>
          <div className="mt-2 space-x-4 text-xs">
            <Link href="#" className="hover:text-blue-400">Privacy Policy</Link>
            <Link href="#" className="hover:text-blue-400">Terms of Service</Link>
          </div>
        </div>
      </footer>
    </div>
  );
}