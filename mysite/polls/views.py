# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import get_object_or_404, render
from django.http import HttpResponseRedirect, HttpResponse
from django.core.urlresolvers import reverse
from django.views.generic import View
from polls.models import Choice, Question


# Create your views here.
def index(request):
    latest_question_list = Question.objects.all().order_by('-pub_date')[:5] #pub_date컬럼의 역순정렬. 상위5개
    context = {'latest_question_list': latest_question_list}
    return render(request, 'polls/index.html', context)


def detail(request, question_id):
    question = get_object_or_404(Question, pk = question_id) #두번째 인자는 검색조건. 조건에 맞는 객체없을 경우 404 exception 발생
    return render(request, 'polls/detail.html', {'question': question})


def vote(request, question_id):
    p = get_object_or_404(Question, pk = question_id)
    try:
        selected_choice = p.choice_set.get(pk=request.POST['choice'])
    except (KeyError, Choice.DoesNotExist):
        #설문 투표 폼을 다시 보여줌
        return render(request, 'polls/detail.html', {
            'question' : p,
            'error_message': "You didn't select a choice.",
        })
    else:
        selected_choice.votes += 1
        selected_choice.save()
        return  HttpResponseRedirect(reverse('polls:results', args=(p.id,)))


def results(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    return render(request, 'polls/results.html', {'question': question})


class MyView(View):
    def get(self, request):
        #뷰 로직 작성
        return HttpResponse('result')