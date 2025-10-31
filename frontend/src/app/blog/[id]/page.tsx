'use client';

import Link from 'next/link';

export default function BlogDetailPage({ params }: { params: { id: string } }) {
  return (
    <div className="antialiased text-gray-800">
      {/* Header & Navigation */}
      <header className="bg-white shadow-md sticky top-0 z-10">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-4 flex justify-between items-center">
          <Link href="/" className="text-2xl font-extrabold text-blue-600 tracking-tight">
            TechVault
          </Link>
          <nav className="hidden md:flex space-x-6">
            <Link href="/" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">Home</Link>
            <Link href="#" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">Topics</Link>
            <Link href="#" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">About</Link>
            <Link href="#" className="text-blue-600 border border-blue-600 px-3 py-1 rounded-full hover:bg-blue-50 transition duration-150">Subscribe</Link>
          </nav>
          <button className="md:hidden text-gray-600 hover:text-blue-600">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
              <path strokeLinecap="round" strokeLinejoin="round" d="M4 6h16M4 12h16m-7 6h7" />
            </svg>
          </button>
        </div>
      </header>

      {/* Main Content */}
      <main className="container mx-auto px-4 sm:px-6 lg:px-8 mt-10 mb-20">
        
        {/* Article Hero Section */}
        <section className="max-w-4xl mx-auto mb-10 text-center">
          <span className="text-sm font-semibold uppercase tracking-wider text-blue-500 bg-blue-100 px-3 py-1 rounded-full">Architecture</span>
          <h1 className="text-4xl sm:text-5xl lg:text-6xl font-extrabold text-gray-900 mt-4 mb-4 leading-tight">
            Mastering the Hexagonal Architecture in React
          </h1>
          <p className="text-xl text-gray-600 mb-6">
            Learn how to separate your core domain logic from infrastructure concerns for scalable and testable applications.
          </p>

          <div className="flex items-center justify-center space-x-4 mb-6">
            <img className="w-12 h-12 rounded-full object-cover shadow-lg" src="https://placehold.co/150x150/4f46e5/ffffff?text=JT" alt="Author Avatar" />
            <div>
              <p className="text-md font-bold text-gray-800">Jane Doe</p>
              <p className="text-sm text-gray-500">October 25, 2025 · 8 min read</p>
            </div>
          </div>

          {/* Featured Image */}
          <img className="w-full h-auto object-cover rounded-xl shadow-xl mt-6" src="https://placehold.co/1000x400/3b82f6/ffffff?text=Hexagonal+Architecture+Diagram" alt="Featured image" />
        </section>

        {/* Content Grid */}
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-10">
          
          {/* Left Column: Share Bar */}
          <aside className="hidden lg:col-span-1 lg:block relative">
            <div className="sticky top-24 flex flex-col items-center space-y-4 pt-10">
              <span className="text-xs font-semibold uppercase text-gray-500 transform -rotate-90 origin-center mb-10">Share</span>
              
              {/* Share Buttons */}
              <button className="text-gray-500 hover:text-blue-500 transition duration-150 p-2 rounded-full hover:bg-gray-100">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2.1"/>
                </svg>
              </button>
              
              <button className="text-gray-500 hover:text-blue-800 transition duration-150 p-2 rounded-full hover:bg-gray-100">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <path d="M16 8a6 6 0 016 6v7h-4v-7a2 2 0 00-2-2 2 2 0 00-2 2v7h-4v-7a6 6 0 016-6z"/>
                  <rect x="2" y="9" width="4" height="12"/>
                  <circle cx="4" cy="4" r="2"/>
                </svg>
              </button>
              
              <button className="text-gray-500 hover:text-green-600 transition duration-150 p-2 rounded-full hover:bg-gray-100">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"/>
                  <path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"/>
                </svg>
              </button>
            </div>
          </aside>

          {/* Main Article Content */}
          <section className="lg:col-span-8 bg-white p-8 rounded-xl shadow-lg">
            <div className="prose max-w-none text-lg text-gray-800 leading-relaxed">
              
              <p className="mb-6">
                The Hexagonal Architecture, often called Ports and Adapters, is a design pattern intended to create highly testable and maintainable applications by isolating the core business logic from external dependencies like databases, UI frameworks, and external APIs. Its core principle is the <strong>Dependency Inversion Principle</strong>.
              </p>

              <h2 className="text-2xl font-bold text-gray-900 mt-8 mb-4 pb-2 border-b-2 border-gray-200">The Role of Ports</h2>
              <p className="mb-4">
                A Port is a simple <strong>interface</strong> or contract that defines an interaction. It dictates <em>what</em> needs to be done. Ports always reside <strong>inside</strong> the hexagon (your core domain).
              </p>
              <p className="mb-6">
                For example, if your domain needs to save data, you define an interface <code className="bg-gray-100 px-2 py-1 rounded">UserRepository</code> (a Secondary/Driven Port) with a method like <code className="bg-gray-100 px-2 py-1 rounded">save(User user)</code>. Your core logic knows nothing about SQL or MongoDB—it just knows it can call <code className="bg-gray-100 px-2 py-1 rounded">save()</code>.
              </p>

              <h2 className="text-2xl font-bold text-gray-900 mt-8 mb-4 pb-2 border-b-2 border-gray-200">The Function of Adapters</h2>
              <p className="mb-4">
                An Adapter is the <strong>implementation</strong> of a Port. It resides <strong>outside</strong> the hexagon and handles the technology-specific translation. It dictates <em>how</em> something gets done.
              </p>
              <ul className="list-disc pl-6 mb-6 space-y-2">
                <li><strong>Driving (Primary) Adapter:</strong> Translates user actions (e.g., HTTP requests, button clicks) into calls to the core's services. (e.g., a <strong>React Controller</strong> handling an API request).</li>
                <li><strong>Driven (Secondary) Adapter:</strong> Translates the core's abstract needs into concrete infrastructure commands. (e.g., a <strong>SQL Repository</strong> writing data to a database).</li>
              </ul>

              <h2 className="text-2xl font-bold text-gray-900 mt-8 mb-4 pb-2 border-b-2 border-gray-200">Implementing in a React Application</h2>
              <p className="mb-4">
                In a React application, this pattern is particularly powerful:
              </p>
              <ol className="list-decimal pl-6 mb-6 space-y-2">
                <li><strong>Core Domain:</strong> Your pure JavaScript/TypeScript logic (hooks, services) with no dependency on React or external libraries.</li>
                <li><strong>Primary Adapters:</strong> React components that translate user interactions into domain calls.</li>
                <li><strong>Secondary Adapters:</strong> API clients, localStorage handlers, or database connectors.</li>
              </ol>

              <div className="bg-blue-50 border-l-4 border-blue-500 p-6 my-8">
                <p className="text-blue-800">
                  <strong>Pro Tip:</strong> By keeping your business logic separate from React, you can easily test it without rendering components, and even reuse the same logic in different frameworks like Vue or Angular.
                </p>
              </div>

              <p className="mb-6">
                This architectural approach ensures that your application remains flexible, testable, and maintainable as it grows in complexity. The hexagonal pattern is particularly valuable in modern web development where requirements and technologies change rapidly.
              </p>
            </div>
          </section>

          {/* Right Sidebar */}
          <aside className="lg:col-span-3 space-y-6">
            
            {/* Author Info */}
            <div className="bg-white p-6 rounded-xl shadow-md">
              <div className="flex items-center space-x-4 mb-4">
                <img className="w-16 h-16 rounded-full object-cover" src="https://placehold.co/150x150/4f46e5/ffffff?text=JT" alt="Author" />
                <div>
                  <h3 className="font-bold text-gray-900">Jane Doe</h3>
                  <p className="text-sm text-gray-600">Senior Developer</p>
                </div>
              </div>
              <p className="text-sm text-gray-600 mb-4">
                Jane is a full-stack developer with 8+ years of experience in React and Node.js. She loves clean architecture and testing.
              </p>
              <button className="w-full bg-blue-600 text-white px-4 py-2 rounded-lg font-medium hover:bg-blue-700 transition duration-150">
                Follow
              </button>
            </div>

            {/* Related Articles */}
            <div className="bg-white p-6 rounded-xl shadow-md">
              <h3 className="text-lg font-bold text-gray-900 mb-4 border-b pb-2">Related Articles</h3>
              <div className="space-y-4">
                <Link href="#" className="block hover:bg-gray-50 p-2 rounded transition duration-150">
                  <h4 className="font-semibold text-sm text-gray-900 mb-1">Clean Code Principles</h4>
                  <p className="text-xs text-gray-600">5 min read</p>
                </Link>
                <Link href="#" className="block hover:bg-gray-50 p-2 rounded transition duration-150">
                  <h4 className="font-semibold text-sm text-gray-900 mb-1">React Testing Best Practices</h4>
                  <p className="text-xs text-gray-600">7 min read</p>
                </Link>
                <Link href="#" className="block hover:bg-gray-50 p-2 rounded transition duration-150">
                  <h4 className="font-semibold text-sm text-gray-900 mb-1">TypeScript Advanced Patterns</h4>
                  <p className="text-xs text-gray-600">6 min read</p>
                </Link>
              </div>
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