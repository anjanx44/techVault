import { Container, Title, Text } from '@mantine/core';

export default function HomePage() {
  return (
      <Container>
        <Title>Welcome to My Blog</Title>
        <Text mt="md">Check out the latest posts on the <a href="/blog">blog page</a>.</Text>
      </Container>
  );
}
