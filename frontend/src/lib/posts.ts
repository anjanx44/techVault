import fs from 'fs';
import path from 'path';
import matter from 'gray-matter';
import { remark } from 'remark';
import html from 'remark-html';

const postsDirectory = path.join(process.cwd(), 'posts');

export function getPostSlugs() {
    return fs.readdirSync(postsDirectory).filter((f) => f.endsWith('.md'));
}

export function getPostBySlug(slug: string) {
    const fullPath = path.join(postsDirectory, slug + '.md');
    const fileContents = fs.readFileSync(fullPath, 'utf8');
    const { data, content } = matter(fileContents);
    return { slug, meta: data, content };
}

export async function getPostContent(slug: string) {
    const { content, ...rest } = getPostBySlug(slug);
    const processedContent = await remark().use(html).process(content);
    return { ...rest, contentHtml: processedContent.toString() };
}

export function getAllPostsMeta() {
    return getPostSlugs().map((slug) => getPostBySlug(slug));
}
