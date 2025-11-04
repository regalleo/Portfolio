#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}🚀 Portfolio Backend Startup Script${NC}"
echo -e "${BLUE}========================================${NC}"

# Set Environment Variables
echo -e "${GREEN}✅ Setting environment variables...${NC}"
export MAIL_HOST="smtp.gmail.com"
export MAIL_PORT="587"
export MAIL_USERNAME="rajsingh170901@gmail.com"
export MAIL_PASSWORD="zgnw myjv hmnh emrk"
export MONGODB_URI="mongodb://localhost:27017/portfolio_db"
export REDIS_HOST="localhost"
export REDIS_PORT="6379"
export MAIL_RECEIVE="rajsingh170901@gmail.com"

echo -e "${GREEN}✅ Environment variables set!${NC}"
echo ""

# Clean and Build
echo -e "${GREEN}🔨 Building project...${NC}"
mvn clean package -DskipTests -q

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Build successful!${NC}"
    echo ""

    # Run the JAR
    echo -e "${BLUE}========================================${NC}"
    echo -e "${GREEN}▶️  Starting Spring Boot Application${NC}"
    echo -e "${BLUE}========================================${NC}"
    echo ""

    java -jar target/portfolio-backend-1.0.0.jar
else
    echo -e "${RED}❌ Build failed!${NC}"
    exit 1
fi
