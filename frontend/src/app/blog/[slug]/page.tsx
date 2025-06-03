import { getPostContent } from '@/lib/posts';
import { Title, Text } from '@mantine/core';
import { notFound } from 'next/navigation';

export default async function PostPage({ params }: { params: { slug: string } }) {
    try {
        const { meta, contentHtml } = await getPostContent(params.slug);
        return (
            <div style={{ padding: 20 }}>
                <Title>{meta.title}</Title>
                <Text c="dimmed" size="sm">{meta.date}</Text>
                <div dangerouslySetInnerHTML={{ __html: contentHtml }} />
            </div>
        );
    } catch (e) {
        return notFound();
    }
}
