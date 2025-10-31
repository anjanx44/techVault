import React, { ReactNode } from 'react';

type AuthLayoutProps = {
    children: ReactNode;
};

/**
 * AuthLayout: Wraps all pages under the (auth) group (/login, /register, etc.).
 * It provides the shared structural elements (Header, Footer) and the global
 * styling wrappers.
 * * Note: The background color and font family are now set globally in globals.css.
 */
const AuthLayout = ({ children }: AuthLayoutProps) => {
    return (
        // 'antialiased' and 'text-gray-800' apply font smoothing and base text color
        <div
            className="antialiased text-gray-800 min-h-screen relative"
        >

            {/* Simple Header based on your original HTML */}
            <header className="bg-white shadow-md">
                <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-4">
                    {/* Using / instead of # for the home link in a React/Next.js app */}
                    <a href="/" className="text-xl font-extrabold text-blue-600 tracking-tight">
                        DevBlog.IO
                    </a>
                </div>
            </header>

            {/* This is where the actual page content (like the Login or Register form)
        will be rendered, specifically within the <main> wrapper defined in
        app/(auth)/login/page.tsx or app/(auth)/register/page.tsx
      */}
            {children}

            {/* Footer placed at the bottom */}
            <footer className="text-center py-4 text-xs text-gray-500 w-full absolute bottom-0">
                &copy; 2025 DevBlog.IO
            </footer>
        </div>
    );
};

export default AuthLayout;
