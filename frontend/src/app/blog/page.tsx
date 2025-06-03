import Link from 'next/link';
import { Card, Text, Title } from '@mantine/core';
import { getAllPostsMeta } from '@/lib/posts';

export default function BlogListPage() {
    const posts = getAllPostsMeta();

    return (
        <div style={{ padding: 20 }}>
            <Title>Blog</Title>
            {posts.map((post) => (
                <Card key={post.slug} shadow="sm" p="lg" my="md">
                    <Link href={`/blog/${post.slug.replace('.md', '')}`}>
                        <Text fw={700} size="lg">{post.meta.title}</Text>
                        <Text size="sm" c="dimmed">{post.meta.date}</Text>
                    </Link>
                </Card>
            ))}
        </div>
    );
}
