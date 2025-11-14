-- Insert sample posts for Latest Articles section
-- Make sure user with id=1 exists (anjanx44)

INSERT INTO post (title, content, user_id, created_at, updated_at, published, featured) VALUES
(
  'Mastering the Hexagonal Architecture in React',
  'Learn how to separate your core domain logic from infrastructure concerns like RoomDB, APIs, and UI frameworks for scalable, testable applications.',
  1,
  NOW(),
  NOW(),
  true,
  true
),
(
  'Why Python is Still the King for Competitive Programming and AI',
  'Transitioning from C++? Understand the performance trade-offs and leverage Python''s powerful libraries like NumPy and Pandas for rapid prototyping.',
  1,
  NOW() - INTERVAL '2 days',
  NOW() - INTERVAL '2 days',
  true,
  false
),
(
  'Compound Interest: The Secret to Long-Term Wealth Generation',
  'We break down the magic of daily compounding and how even small principal amounts can grow significantly over time using the right investment vehicles.',
  1,
  NOW() - INTERVAL '1 week',
  NOW() - INTERVAL '1 week',
  true,
  false
),
(
  'Interface vs. Abstract Class: Which to Choose for Your Architecture',
  'A clear look at the Contract vs. Base dilemma in OOP and how to apply these concepts in Java or TypeScript for clean, maintainable code.',
  1,
  NOW() - INTERVAL '2 weeks',
  NOW() - INTERVAL '2 weeks',
  true,
  false
);