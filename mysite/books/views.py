# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.views.generic.base import TemplateView
from django.views.generic import ListView
from django.views.generic import DetailView
from books.models import Book, Author, Publisher


# Create your views here.
class BooksModelView(TemplateView):
    template_name = 'books/index.html'  # TemplateView사용시 필수적으로 template_name 오버라이딩

    def get_context_data(self, **kwargs):  # 템플릿 시스템 넘겨줄 컨텍스트 변수있을경우 오버라이딩
        context = super(BooksModelView, self).get_context_data(**kwargs)  # get_context_data정의시 반드시 첫줄에 super()호출
        context['object_list'] = ['Book', 'Author', 'Publisher']
        return context


# Book 테이블 모두 가져와 object_list 변수 자동 생성. 템플릿파일 자동지정. books/book_list.html(모델명 소문자_list.html)
# ListView가 위작업 해줌.
class BookList(ListView):
    model = Book


class AuthorList(ListView):
    model = Author


class PublisherList(ListView):
    model = Publisher


# 컨텍스트 변수 object 자동 생성. 템플릿 파일 자동지정. 모델명 소문자_list.html. DetailView가 해줌
class BookDetail(DetailView):
    model = Book


class AuthorDetail(DetailView):
    model = Author


class PublisherDetail(DetailView):
    model = Publisher
