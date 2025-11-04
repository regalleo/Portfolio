// ==============================
// Portfolio Database Initialization
// ==============================
db = db.getSiblingDB('portfolio_db');

// Clear existing data
db.about.deleteMany({});
db.skills.deleteMany({});
db.projects.deleteMany({});
db.experiences.deleteMany({});

// ==============================
// ABOUT SECTION
// ==============================
db.about.insertOne({
  name: "Raj Shekhar Singh",
  title: "Full-Stack Developer | Data Engineer | Gen-AI Specialist",
  bio: "B.Tech EEE graduate from PES University specializing in intelligent systems at scale. I architect real-time data pipelines processing terabytes daily with Kafka and Spark, build lightning-fast React applications with Spring Boot backends, and deploy Gen-AI solutions leveraging RAG, LangChain, and vector databases. From microservices optimization to AI-powered automation—I turn complex technical challenges into production-grade solutions that deliver measurable impact.",
  tagline: "Building scalable full-stack platforms • Real-time big data pipelines • Gen-AI & RAG systems",
  expertise: "Full-Stack Development (React, Spring Boot, REST APIs, Microservices) • Big Data Engineering (Kafka, Spark, Azure Data Lake, ETL pipelines handling 1TB+ daily) • Gen-AI & RAG (LangChain, Vector Databases, Retrieval-Augmented Generation) • AI Automation (Fine-tuned LLMs, Prompt Engineering, Hugging Face) • Cloud & DevOps (AWS, Azure, Docker, Kubernetes, CI/CD)",
  currentFocus: "Exploring RAG architectures with Pinecone & ChromaDB, building AI agents with tool calling, scaling Kafka streams for real-time analytics, and deploying Gen-AI models in production.",
  philosophy: "Great engineering isn't just clean code—it's measurable impact. Every pipeline I build, every API I optimize, and every Gen-AI model I deploy answers one question: How can this make workflows 10x more efficient?",
  availability: "Open to full-time roles, consulting projects, and collaborations at the intersection of AI, big data, and intelligent automation.",
  email: "rajsingh170901@gmail.com",
  phone: "+91-8840082361",
  location: "Bangalore, India",
  profileImage: "https://ui-avatars.com/api/?name=Raj+Shekhar+Singh&size=400&background=0D1117&color=FF6B00",
  resumeUrl: "/resume.pdf",
  githubUrl: "https://github.com/regalleo",
  linkedinUrl: "https://www.linkedin.com/in/raj-shekhar-singh-aa16ab245/",
  portfolioUrl: "https://rajshekhar.dev",
  stats: {
    dataProcessed: "1TB+",
    usersImpacted: "500K+",
    latencyReduction: "45%",
    performanceScore: "95%"
  }
});

// ==============================
// SKILLS SECTION
// ==============================
db.skills.insertMany([
  {
    name: "Java",
    category: "Programming Languages",
    proficiency: 90,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg",
    yearsOfExperience: 4
  },
  {
    name: "Python",
    category: "Programming Languages",
    proficiency: 88,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/python/python-original.svg",
    yearsOfExperience: 4
  },
  {
    name: "Spring Boot",
    category: "Backend Frameworks",
    proficiency: 92,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg",
    yearsOfExperience: 3
  },
  {
    name: "React.js",
    category: "Frontend Frameworks",
    proficiency: 88,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg",
    yearsOfExperience: 3
  },
  {
    name: "MySQL",
    category: "Databases",
    proficiency: 90,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg",
    yearsOfExperience: 4
  },
  {
    name: "MongoDB",
    category: "Databases",
    proficiency: 85,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original.svg",
    yearsOfExperience: 3
  },
  {
    name: "Apache Kafka",
    category: "Big Data & Cloud",
    proficiency: 88,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apachekafka/apachekafka-original.svg",
    yearsOfExperience: 2
  },
  {
    name: "Apache Spark",
    category: "Big Data & Cloud",
    proficiency: 90,
    iconUrl: "https://www.google.com/s2/favicons?domain=spark.apache.org&sz=128",
    yearsOfExperience: 2
  },
  {
    name: "Docker",
    category: "DevOps & Tools",
    proficiency: 75,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg",
    yearsOfExperience: 1
  },
  {
    name: "Git & GitHub",
    category: "Version Control",
    proficiency: 90,
    iconUrl: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg",
    yearsOfExperience: 4
  }
]);

// ==============================
// PROJECTS SECTION (UPDATED WITH 7 PROJECTS)
// ==============================
db.projects.insertMany([
  {
    title: "Professional Portfolio Website",
    description: "Full-stack personal portfolio and resume website showcasing projects, skills, and experience. Built with React frontend, Node.js backend, and MongoDB database. Features dynamic project showcase, interactive skills section, real-time project data synchronization, and professional styling with modern animations.",
    imageUrl: "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?w=800",
    githubLink: "https://github.com/regalleo/portfolio-website",
    liveLink: "https://rajshekhar.dev",
    technologies: ["React.js", "Node.js", "Express", "MongoDB", "CSS3", "REST API", "Docker"],
    category: "web",
    completedDate: new Date("2025-11-04"),
    featured: true,
    stats: {
      projects: "7+ showcase",
      skills: "15+ technologies",
      loadTime: "<1 second",
      mobileReady: "100% responsive"
    },
    highlights: [
      "Dynamic project showcase pulling live data from MongoDB",
      "Interactive skills matrix with proficiency levels",
      "Real-time resume and project updates",
      "Professional animations and glassmorphism design",
      "SEO optimized for recruiter discovery",
      "Mobile-responsive design for all devices"
    ]
  },
  {
    title: "Customer 360 Analytics Platform",
    description: "Production-grade big data analytics platform processing real-time customer data streams. Implements real-time stream processing with Apache Kafka (6+ events/sec), distributed computing with Apache Spark for scalable data processing, MongoDB for flexible data storage, machine learning-powered churn predictions (85%+ accuracy), and an interactive Flask dashboard with professional UI.",
    imageUrl: "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800",
    githubLink: "https://github.com/regalleo/customer360-platform",
    liveLink: "https://customer360-brxq.onrender.com/",
    technologies: ["Kafka", "Spark", "MongoDB", "Python", "Flask", "scikit-learn", "Docker", "Render"],
    category: "data",
    completedDate: new Date("2025-11-03"),
    featured: true,
    stats: {
      eventsPerSecond: "6+",
      processingLatency: "<2 seconds",
      mongoDocuments: "1000+ users",
      modelAccuracy: "85%+",
      dashboardLoadTime: "<1 second"
    },
    highlights: [
      "Real-time Kafka stream processing handling 600,000+ events daily",
      "Apache Spark distributed processing with optimized micro-batching",
      "MongoDB indexing for 10x faster query performance",
      "Machine learning churn prediction model with 85%+ accuracy",
      "Production deployment on Render with Docker containerization",
      "Netflix-style UI with Chart.js data visualization"
    ]
  },
  {
    title: "Task Manager Dashboard",
    description: "Beautiful, interactive full-stack task management application with animated UI and AI-powered suggestions. Features stunning visual experience with animated network background, staircase visualization with character progression, glassmorphism design, and smooth animations. Integrated with Hugging Face AI for intelligent task suggestions and Pixabay API for dynamic task images.",
    imageUrl: "https://images.unsplash.com/photo-1555056169-2b2477e0c0e7?w=800",
    githubLink: "https://github.com/regalleo/springboot_django_full_stack",
    liveLink: "https://springboot-django-full-stack-2.onrender.com/",
    technologies: ["Spring Boot", "Java 17", "Django", "JavaScript", "Bootstrap 5", "Hugging Face API", "Pixabay API", "Render"],
    category: "web",
    completedDate: new Date("2025-10-15"),
    featured: true,
    stats: {
      animationFramerate: "60 FPS",
      apiResponses: "<200ms",
      taskManagementFeatures: "CRUD + Priority + Status",
      aiIntegration: "Hugging Face GPT-2"
    },
    highlights: [
      "Animated network background with particle connections",
      "Interactive staircase visualization with character progression",
      "Glassmorphism UI design with modern aesthetics",
      "AI-powered task suggestions using Hugging Face",
      "Dynamic task images from Pixabay API",
      "Full-stack deployment on Render"
    ]
  },
  {
    title: "RAG-Powered AI Knowledge Assistant",
    description: "Enterprise-grade AI knowledge assistant that reads and understands internal documents, enabling users to ask natural language questions and receive context-rich, document-backed answers. Implements Retrieval-Augmented Generation (RAG) combining vector databases, embeddings, and LLMs for accurate information retrieval from organizational knowledge bases.",
    imageUrl: "https://images.unsplash.com/photo-1677442d019cecf8e5c1a86766ab629f?w=800",
    githubLink: "https://github.com/regalleo/rag-ai-assistant",
    liveLink: null,
    technologies: ["LangChain", "FastAPI", "ChromaDB", "GPT-4", "Python", "Docker", "Vector Embeddings"],
    category: "ai",
    completedDate: new Date("2025-09-20"),
    featured: true,
    stats: {
      documentRetrieval: "Top-K vector similarity",
      responseAccuracy: "90%+",
      supportedDocuments: "PDF, TXT, Docx",
      queryLatency: "<1 second"
    },
    highlights: [
      "LangChain document loader for multiple formats",
      "Advanced document chunking and semantic splitting",
      "Vector embeddings using state-of-the-art models",
      "ChromaDB for efficient similarity search",
      "Integration with GPT-4 for natural language generation",
      "FastAPI backend with REST endpoints"
    ]
  },
  {
    title: "Credit Card Fraud Detection System",
    description: "Data science project implementing machine learning algorithms for credit card fraud detection. Uses anonymized transaction data from Kaggle with advanced outlier detection algorithms (Isolation Forest, Local Outlier Factor) achieving high accuracy in identifying fraudulent transactions.",
    imageUrl: "https://images.unsplash.com/photo-1551734170-f3f8b83b1166?w=800",
    githubLink: "https://github.com/regalleo/credit_card_fraud_detection",
    liveLink: null,
    technologies: ["Python", "scikit-learn", "pandas", "numpy", "matplotlib", "seaborn", "Jupyter Notebook"],
    category: "data",
    completedDate: new Date("2025-08-10"),
    featured: true,
    stats: {
      datasetSize: "280,000+ transactions",
      classImbalance: "0.1% fraud",
      modelAccuracy: "99.5%+",
      precision: "98%",
      recall: "92%"
    },
    highlights: [
      "Comprehensive exploratory data analysis (EDA)",
      "Data preprocessing and class imbalance handling",
      "Isolation Forest algorithm for anomaly detection",
      "Local Outlier Factor (LOF) density-based detection",
      "PCA dimensionality reduction",
      "Classification metrics: accuracy, precision, recall, F1-score"
    ]
  },
  {
    title: "Tesla Stock Data Analysis",
    description: "Data analysis project analyzing Tesla stock trends and patterns using yfinance library. Implements data fetching, preprocessing, and visualization techniques to identify stock market trends and generate actionable insights from historical price data.",
    imageUrl: "https://images.unsplash.com/photo-1611974888872-c34db9eebf5e?w=800",
    githubLink: "https://github.com/regalleo/Tesla_stock_analysis",
    liveLink: null,
    technologies: ["Python", "yfinance", "pandas", "matplotlib", "numpy"],
    category: "data",
    completedDate: new Date("2025-07-25"),
    featured: false,
    stats: {
      dataPoints: "10+ years historical",
      updateFrequency: "Daily",
      visualizations: "5+ charts",
      technicalIndicators: "Moving averages, trends"
    },
    highlights: [
      "Real-time stock data fetching from Yahoo Finance",
      "Time-series data analysis and trend identification",
      "DataFrame manipulation with pandas",
      "Professional visualizations",
      "Technical analysis indicators"
    ]
  },
  {
    title: "Construction Management System",
    description: "Complete construction project management solution handling assets, employee records, project tracking, and progress visualization. Built using Java and MySQL with comprehensive REST APIs and real-time updates.",
    imageUrl: "https://images.unsplash.com/photo-1503387762-592deb58ef4e?w=800",
    githubLink: "https://github.com/regalleo/construction-management-system",
    liveLink: null,
    technologies: ["Java", "Spring Boot", "MySQL", "React.js", "REST API"],
    category: "web",
    completedDate: new Date("2025-02-10"),
    featured: true
  }
]);

// ==============================
// EXPERIENCE SECTION
// ==============================
db.experiences.insertMany([
  {
    company: "Infosys Limited",
    position: "Specialist Programmer Intern",
    description:
      "Worked on backend microservices and data engineering modules using Spring Boot, Apache Kafka, and Azure Data Lake. Designed efficient APIs and developed real-time event-driven workflows integrating Spark and NoSQL systems.",
    startDate: new Date("2025-06-01"),
    endDate: new Date("2025-09-30"),
    location: "Mysuru, India",
    achievements: [
      "Reduced data processing latency by 35% through Spark and Kafka optimization",
      "Achieved 40% higher throughput in ETL pipelines using Azure Data Lake Gen2",
      "Implemented Generative AI-driven reporting automation reducing analytics time by 30%",
      "Collaborated with CI/CD teams accelerating deployment cycles by 25%"
    ]
  },
  {
    company: "CodeClause",
    position: "Data Science Intern",
    description:
      "Worked on large-scale data analytics and business intelligence solutions using Python, Pandas, and data visualization tools. Focused on analyzing Flipkart sales data and automating KPI dashboards.",
    startDate: new Date("2023-06-01"),
    endDate: new Date("2023-08-31"),
    location: "Remote",
    achievements: [
      "Analyzed over 1 million records of Flipkart sales data using Python and Pandas",
      "Improved marketing ROI by 15% by uncovering key business insights",
      "Automated KPI dashboards reducing reporting time by 40%",
      "Enhanced executive decision-making efficiency through visualization-driven analytics"
    ]
  },
  {
    company: "Surat Constructions Pvt. Ltd.",
    position: "Software Intern",
    description:
      "Developed an end-to-end construction management system to handle assets, employee records, and project analytics using Java and MySQL stack.",
    startDate: new Date("2024-03-01"),
    endDate: new Date("2024-07-31"),
    location: "Surat, India",
    achievements: [
      "Automated data workflows reducing manual entry by 50%",
      "Created modular REST APIs for scalable construction data operations",
      "Integrated Python-based visualization for real-time project progress tracking"
    ]
  },
  {
    company: "PES University",
    position: "B.Tech in Electrical and Electronics Engineering",
    description:
      "Undergraduate coursework focused on software development, data-driven systems, and full-stack technologies.",
    startDate: new Date("2021-12-01"),
    endDate: new Date("2025-05-31"),
    location: "Bangalore, India",
    achievements: [
      "Received DAC Scholarship for 5th and 6th semesters",
      "Top 30 in CyberSecurity Hackathon (ISFCR, PES University)",
      "Consolation Prize in HackEEE Hackathon"
    ]
  }
]);

// ==============================
// CONFIRMATION LOGS
// ==============================
print("✅ Database initialized successfully!");
print("About:", db.about.countDocuments());
print("Skills:", db.skills.countDocuments());
print("Projects:", db.projects.countDocuments());
print("Experiences:", db.experiences.countDocuments());
