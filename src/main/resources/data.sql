INSERT INTO todo (username, description, target_date, done) VALUES
-- admin
('admin', 'Set up project repo', '2025-05-01', false),
('admin', 'Review team PRs', '2025-05-02', true),
('admin', 'Update documentation', '2025-05-03', false),
('admin', 'Refactor login module', '2025-05-04', false),
('admin', 'Schedule sprint planning', '2025-05-05', true),
('admin', 'Clean up old branches', '2025-05-06', false),
('admin', 'Check security audit', '2025-05-07', false),

-- admin2
('admin2', 'Write integration tests', '2025-05-01', true),
('admin2', 'Optimize query performance', '2025-05-02', false),
('admin2', 'Merge feature-branch', '2025-05-03', true),
('admin2', 'Fix UI bugs in dashboard', '2025-05-04', false),
('admin2', 'Enable logging', '2025-05-05', true),
('admin2', 'Deploy to staging', '2025-05-06', false),
('admin2', 'Code review for edmo', '2025-05-07', false),

-- edmo
('edmo', 'Build homepage layout', '2025-05-01', false),
('edmo', 'Fix navbar alignment', '2025-05-02', false),
('edmo', 'Connect backend API', '2025-05-03', true),
('edmo', 'Implement login validation', '2025-05-04', false),
('edmo', 'Add unit tests for service layer', '2025-05-05', false),
('edmo', 'Write blog post on Spring Boot', '2025-05-06', true);