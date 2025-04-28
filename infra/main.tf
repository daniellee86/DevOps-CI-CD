provider "aws" {
  region = var.aws_region
}

resource "aws_instance" "web" {
  ami                    = "ami-0eb260c4d5475b901"
  instance_type          = "t2.micro"
  key_name               = var.key_pair_name
  vpc_security_group_ids = [aws_security_group.web_sg.id]

  user_data = <<-EOF
              #!/bin/bash
              sudo apt update -y
              sudo apt install openjdk-17-jre-headless -y
              mkdir -p /home/ubuntu/app
              chown -R ubuntu:ubuntu /home/ubuntu/app
              EOF

  tags = {
    Name = "devops-cicd"
  }
}

resource "aws_security_group" "web_sg" {
  name_prefix = "devops-sg"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8084
    to_port     = 8084
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

output "instance_ip" {
  value = aws_instance.web.public_ip
}