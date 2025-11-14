'use client';

import Link from 'next/link';
import { useState, useEffect } from 'react';
import { notFound } from 'next/navigation';

export default function PostPage({ params }: { params: { slug: string } }) {
    const [post, setPost] = useState<any>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch(`http://localhost:8080/api/v1/posts/${params.slug}`)
            .then(res => {
                if (!res.ok) throw new Error('Post not found');
                return res.json();
            })
            .then(data => {
                setPost(data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching post:', err);
                setLoading(false);
            });
    }, [params.slug]);

    if (loading) {
        return (
            <div className="min-h-screen flex items-center justify-center">
                <div className="text-xl">Loading...</div>
            </div>
        );
    }

    if (!post) {
        return notFound();
    }

    return (
        <div className="antialiased text-gray-800" style={{ backgroundColor: '#f7f9fb' }}>
            {/* Header & Navigation */}
            <header className="bg-white shadow-md sticky top-0 z-10">
                <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-4 flex justify-between items-center">
                    <Link href="/" className="text-2xl font-extrabold text-blue-600 tracking-tight">
                        TechVault
                    </Link>
                    <nav className="hidden md:flex space-x-6">
                        <Link href="/" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">Home</Link>
                        <Link href="/blog" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">Blog</Link>
                        <Link href="#" className="text-gray-600 hover:text-blue-600 transition duration-150 font-medium">About</Link>
                        <Link href="/login" className="text-blue-600 border border-blue-600 px-3 py-1 rounded-full hover:bg-blue-50 transition duration-150">Login</Link>
                    </nav>
                </div>
            </header>

            {/* Main Content */}
            <main className="container mx-auto px-4 sm:px-6 lg:px-8 mt-10 mb-20">
                {/* Article Hero Section */}
                <section className="max-w-4xl mx-auto mb-10 text-center">
                    {post.featured && (
                        <span className="text-sm font-semibold uppercase tracking-wider text-blue-500 bg-blue-100 px-3 py-1 rounded-full">Featured</span>
                    )}
                    <h1 className="text-4xl sm:text-5xl lg:text-6xl font-extrabold text-gray-900 mt-4 mb-4 leading-tight">
                        {post.title}
                    </h1>
                    <p className="text-xl text-gray-600 mb-6">
                        {post.content.substring(0, 200)}...
                    </p>

                    <div className="flex items-center justify-center space-x-4 mb-6">
                        <img className="w-12 h-12 rounded-full object-cover shadow-lg" src="https://placehold.co/150x150/4f46e5/ffffff?text=A" alt="Author Avatar" />
                        <div>
                            <p className="text-md font-bold text-gray-800">{post.user.firstName || post.user.username}</p>
                            <p className="text-sm text-gray-500">{new Date(post.createdAt).toLocaleDateString()} · 8 min read</p>
                        </div>
                    </div>

                    {/* Featured Image */}
                    <img className="w-full h-auto object-cover rounded-xl shadow-xl mt-6" src="https://placehold.co/1000x400/3b82f6/ffffff?text=Article+Image" alt="Featured image" />
                </section>

                {/* Article Content */}
                <div className="grid grid-cols-1 lg:grid-cols-12 gap-10">
                    {/* Share Bar */}
                    <aside className="hidden lg:col-span-1 lg:block relative">
                        <div className="sticky top-24 flex flex-col items-center space-y-4 pt-10">
                            <span className="text-xs font-semibold uppercase text-gray-500 transform -rotate-90 origin-center mb-10">Share</span>
                            <button className="text-gray-500 hover:text-blue-500 transition duration-150 p-2 rounded-full hover:bg-gray-100">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                                    <path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2"/>
                                </svg>
                            </button>
                        </div>
                    </aside>

                    {/* Main Article Content */}
                    <section className="lg:col-span-8 bg-white p-8 rounded-xl shadow-lg">
                        <div className="prose max-w-none text-lg text-gray-800" style={{ lineHeight: '1.75' }}>
                            <p className="whitespace-pre-wrap">{post.content}</p>
                        </div>
                    </section>

                    {/* Right Sidebar */}
                    <aside className="lg:col-span-3 space-y-6">
                        <div className="bg-white p-6 rounded-xl shadow-md">
                            <h3 className="text-lg font-bold text-gray-900 mb-4">About the Author</h3>
                            <div className="flex items-center space-x-3">
                                <img className="w-12 h-12 rounded-full" src="https://placehold.co/150x150/4f46e5/ffffff?text=A" alt="Author" />
                                <div>
                                    <p className="font-semibold">{post.user.firstName || post.user.username}</p>
                                    <p className="text-sm text-gray-600">{post.user.email}</p>
                                </div>
                            </div>
                        </div>

                        <div className="bg-white p-6 rounded-xl shadow-md">
                            <h3 className="text-lg font-bold text-gray-900 mb-4">Related Articles</h3>
                            <div className="space-y-3">
                                <Link href="#" className="block text-sm text-blue-600 hover:underline">Understanding Clean Architecture</Link>
                                <Link href="#" className="block text-sm text-blue-600 hover:underline">React Best Practices 2024</Link>
                                <Link href="#" className="block text-sm text-blue-600 hover:underline">TypeScript Design Patterns</Link>
                            </div>
                        </div>
                    </aside>
                </div>
            </main>
        </div>
    );
}